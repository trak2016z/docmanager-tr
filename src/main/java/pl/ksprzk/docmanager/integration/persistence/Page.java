package pl.ksprzk.docmanager.integration.persistence;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class Page {

   private int number;
   private int size;
   private long total;

   public int getNumber() {
      return number;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public int getSize() {
      return size;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public long getTotal() {
      return total;
   }

   public void setTotal(long total) {
      this.total = total;
   }

}
