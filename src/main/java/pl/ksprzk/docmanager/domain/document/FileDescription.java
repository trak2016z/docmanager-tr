package pl.ksprzk.docmanager.domain.document;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class FileDescription {
   private String extension;
   private String filename;

   public String getExtension() {
      return extension;
   }

   public void setExtension(String extension) {
      this.extension = extension;
   }

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public FileDescription() {
   }

   public FileDescription(String extension, String filename) {
      this.extension = extension;
      this.filename = filename;
   }
   
}
