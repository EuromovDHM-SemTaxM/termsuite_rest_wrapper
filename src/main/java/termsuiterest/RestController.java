package termsuiterest;

import fr.univnantes.termsuite.api.ExtractorOptions;

import fr.univnantes.termsuite.api.IndexedCorpusIO;
import fr.univnantes.termsuite.api.TermSuite;
import fr.univnantes.termsuite.index.Terminology;
import fr.univnantes.termsuite.io.json.JsonOptions;
import fr.univnantes.termsuite.io.json.JsonTerminologyIO;
import fr.univnantes.termsuite.model.IndexedCorpus;
import fr.univnantes.termsuite.model.Lang;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Operation(summary = "Extracts terminology using TermSuite", description = "This is a REST wrapper for TermSuite.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully extracted terminology"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @RequestMapping(value = "/extract_terminology", method = RequestMethod.POST,
            produces = {"application/json"},
            consumes = {"plain/text"}
    )
    public String extractTerminology(
            @Parameter(description = "Text corpus from which to extract terms") @RequestBody String corpus_text,
            @Parameter(name = "language", required = true,
                    schema = @Schema(description = "Language", type = "string", defaultValue = "en",
                            allowableValues = {"en", "es", "de", "fr", "ru"})) @RequestParam("language") String language) {
        Lang lang = Lang.fromCode(language);

        ExtractorOptions extractorOptions = TermSuite.getDefaultExtractorConfig(lang);
        extractorOptions.getPostProcessorConfig().setEnabled(true);
        extractorOptions.getGathererConfig().setSemanticEnabled(true);


        StringCorpus corpus = new StringCorpus(lang, corpus_text);

        IndexedCorpus indexedCorpus = TermSuite.preprocessor()
                .setTaggerPath(Path.of("/opt/treetagger"))
                .toIndexedCorpus(corpus, 500000);

        Terminology terminology = indexedCorpus.getTerminology();


        TermSuite.terminoExtractor()
                .setOptions(extractorOptions)
                .execute(indexedCorpus);

        String response = "{}";

        StringWriter writer = new StringWriter();
        try {
            JsonTerminologyIO.save(writer, indexedCorpus, new JsonOptions());
        } catch (IOException e) {
            e.printStackTrace();
        }

        response = writer.toString();

        return response;
    }
}
