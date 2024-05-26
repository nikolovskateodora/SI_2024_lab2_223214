import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;


public class SILab2Test {

    @Test
    void TestEveryBranch() {
        //1. Test case: Null item list
        List<Item> ListItemNull = null;
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(ListItemNull, 200), "allItems list can't be null!");

        //2. Test case: Insufficient payment
        List<Item> itemListInsufficientPayment = new ArrayList<>();
        itemListInsufficientPayment.add(new Item("Item1", "123456789", 400, 0));
        assertFalse(SILab2.checkCart(itemListInsufficientPayment, 200), "Insufficient payment");

        //3. Test case: Empty item list
        assertTrue(SILab2.checkCart(new ArrayList<>(), 100), "allItems list can't be null!");

        //4. Test case: Valid item list
        List<Item> itemListValid = new ArrayList<>();
        itemListValid.add(new Item("Item1", "123456789", 200, 0.1f));
        itemListValid.add(new Item("Item2", "112345678", 400, 0));
        assertTrue(SILab2.checkCart(itemListValid, 1000), "Valid item list");

        //5. Test case: Invalid barcode
        List<Item> itemListInvalidBarcode = new ArrayList<>();
        itemListInvalidBarcode.add(new Item("Item1", "1C2345678", 500, 0.2f));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemListInvalidBarcode, 1000), "Invalid character in item barcode!");

        //6. Test case: Price over 300 with discount and barcode starting with zero
        List<Item> itemListPriceOver300 = new ArrayList<>();
        itemListPriceOver300.add(new Item("Item1", "0123456789", 600, 0.1f));
        assertTrue(SILab2.checkCart(itemListPriceOver300, 350), "Expected true for price over 300 with discount and barcode starting with zero");

        //7. Test case: Item name is null, should set to "unknown"
        List<Item> itemListNullName = new ArrayList<>();
        Item nullNameItem = new Item(null, "123456789", 500, 0.1f);
        itemListNullName.add(nullNameItem);
        SILab2.checkCart(itemListNullName, 1000);
        assertEquals("unknown", nullNameItem.getName(), "Expected item name to be set to 'unknown'");

        //8. Test case: No barcode
        List<Item> itemListNoBarcode = new ArrayList<>();
        itemListNoBarcode.add(new Item("Item1", null, 400, 0.1f));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemListNoBarcode, 100), "No barcode!");

    }
    @Test
    void TestMultipleConditions() {
        //Minimum test-cases
//        List<Item> itemListCase1 = new ArrayList<>();
//        itemListCase1.add(new Item("Item1", "0123456789", 400, 0.1f));
//        itemListCase1.add(new Item("Item2", "1123456789", 400, 0.1f));
//        itemListCase1.add(new Item("Item3", "1123456789", 400, -1));
//        itemListCase1.add(new Item("Item4", "1123456789", 200, 0.1f));
//        assertTrue(SILab2.checkCart(itemListCase1, 1000), "Expected true");

        //1. Test case: price>300, discount>0, first barcode character ="0"
        List<Item> itemListCase1 = new ArrayList<>();
        itemListCase1.add(new Item("Item1", "0123456789", 400, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase1, 1000), "Expected true");

        //2. Test case: price>300, discount>0, first barcode character !="0"
        List<Item> itemListCase2 = new ArrayList<>();
        itemListCase2.add(new Item("Item1", "1123456789", 400, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase2, 1000), "Expected true");

        //3. Test case: price>300, discount==0, first barcode character ="0"
        List<Item> itemListCase3 = new ArrayList<>();
        itemListCase3.add(new Item("Item1", "0123456789", 400, 0));
        assertTrue(SILab2.checkCart(itemListCase3, 1000), "Expected true");

        //4. Test case: price>300, discount==0, first barcode character !="0"
        List<Item> itemListCase4 = new ArrayList<>();
        itemListCase4.add(new Item("Item1", "1123456789", 400, 0));
        assertTrue(SILab2.checkCart(itemListCase4, 1000), "Expected true");

        //5. Test case: price==300, discount>0, first barcode character ="0"
        List<Item> itemListCase5 = new ArrayList<>();
        itemListCase5.add(new Item("Item1", "0123456789", 300, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase5, 1000), "Expected true");

        //6. Test case: price==300, discount>0, first barcode character !="0"
        List<Item> itemListCase6 = new ArrayList<>();
        itemListCase6.add(new Item("Item1", "1123456789", 300, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase6, 1000), "Expected true");

        //7. Test case: price==300, discount==0, first barcode character ="0"
        List<Item> itemListCase7 = new ArrayList<>();
        itemListCase7.add(new Item("Item1", "0123456789", 300, 0));
        assertTrue(SILab2.checkCart(itemListCase7, 1000), "Expected true");

        //8. Test case: price==300, discount==0, first barcode character !="0"
        List<Item> itemListCase8 = new ArrayList<>();
        itemListCase8.add(new Item("Item1", "1123456789", 300, 0));
        assertTrue(SILab2.checkCart(itemListCase8, 1000), "Expected true");

    }
}