package termsuiterest;

import fr.univnantes.termsuite.api.TextualCorpus;
import fr.univnantes.termsuite.model.Document;
import fr.univnantes.termsuite.model.Lang;


import java.util.HashMap;
import java.util.stream.Stream;


public class StringCorpus implements TextualCorpus {

    private final String rawCorpus;
    private final Lang lang;

    private final HashMap<Document, String> documentIndex;

    public StringCorpus(Lang lang, String rawCorpus) {
        this.rawCorpus = rawCorpus;
        this.lang = lang;

        documentIndex = new HashMap<>();

        String[] lines = rawCorpus.split("\n");
        int documentId = 0;
        for (String line : lines) {
            if (line.strip().length() > 0) {
                final Document document = new Document(lang, String.valueOf(documentId));
                documentIndex.put(document, line);
                documentId++;
            }
        }
    }

    @Override
    public int getNbDocuments() {
        return documentIndex.size();
    }

    @Override
    public long getTotalSize() {
        return rawCorpus.length();
    }

    @Override
    public Stream<Document> documents() {
        return documentIndex.keySet().stream();
    }

    @Override
    public String readDocumentText(Document doc) {
        return documentIndex.get(doc);
    }

    @Override
    public Lang getLang() {
        return lang;
    }
}
