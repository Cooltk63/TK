import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

public String cleanHtml(String rawHtml) {
    Document doc = Jsoup.parse(rawHtml);
    doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml); // Makes tags self-closed
    doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
    return doc.html();
}