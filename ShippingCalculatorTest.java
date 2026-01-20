public class ShippingCalculatorTest {

    public static void main(String[] args) {
        System.out.println("=== การทดสอบระบบคำนวณค่าจัดส่ง ===\n");

        // Test Case 1: Bangkok area, 0.5 kg, no express, no VIP
        testCalculation(true, 0.5, false, false, 40.0, "Test 1: Bangkok 0.5kg standard");

        // Test Case 2: Bangkok area, 2 kg, express, no VIP
        testCalculation(true, 2.0, true, false, 90.0, "Test 2: Bangkok 2kg express");

        // Test Case 3: Bangkok area, 4 kg, no express, VIP
        testCalculation(true, 4.0, false, true, 64.0, "Test 3: Bangkok 4kg VIP");

        // Test Case 4: Province, 0.8 kg, no express, no VIP
        testCalculation(false, 0.8, false, false, 60.0, "Test 4: Province 0.8kg standard");

        // Test Case 5: Province, 3.5 kg, express, VIP
        testCalculation(false, 3.5, true, true, 120.0, "Test 5: Province 3.5kg express VIP");

        // Test Case 6: Bangkok area, 6 kg, express, VIP
        testCalculation(true, 6.0, true, true, 104.0, "Test 6: Bangkok 6kg express VIP");

        System.out.println("\n=== การทดสอบเสร็จสิ้น ===");
    }

    private static void testCalculation(boolean isBangkokArea, double weight,
            boolean isExpress, boolean isVIP,
            double expectedCost, String testName) {

        // Constants from main program
        final double BANGKOK_WEIGHT_0_1_KG = 40.0;
        final double BANGKOK_WEIGHT_1_3_KG = 60.0;
        final double BANGKOK_WEIGHT_3_5_KG = 80.0;
        final double BANGKOK_WEIGHT_ABOVE_5_KG = 100.0;

        final double PROVINCE_WEIGHT_0_1_KG = 60.0;
        final double PROVINCE_WEIGHT_1_3_KG = 90.0;
        final double PROVINCE_WEIGHT_3_5_KG = 120.0;
        final double PROVINCE_WEIGHT_ABOVE_5_KG = 150.0;

        final double EXPRESS_SERVICE_FEE = 30.0;
        final double VIP_DISCOUNT_RATE = 0.20;

        // Calculate base cost
        double baseCost;
        if (isBangkokArea) {
            if (weight <= 1) {
                baseCost = BANGKOK_WEIGHT_0_1_KG;
            } else if (weight <= 3) {
                baseCost = BANGKOK_WEIGHT_1_3_KG;
            } else if (weight <= 5) {
                baseCost = BANGKOK_WEIGHT_3_5_KG;
            } else {
                baseCost = BANGKOK_WEIGHT_ABOVE_5_KG;
            }
        } else {
            if (weight <= 1) {
                baseCost = PROVINCE_WEIGHT_0_1_KG;
            } else if (weight <= 3) {
                baseCost = PROVINCE_WEIGHT_1_3_KG;
            } else if (weight <= 5) {
                baseCost = PROVINCE_WEIGHT_3_5_KG;
            } else {
                baseCost = PROVINCE_WEIGHT_ABOVE_5_KG;
            }
        }

        // Add express fee
        double expressFee = isExpress ? EXPRESS_SERVICE_FEE : 0;
        double subtotal = baseCost + expressFee;

        // Apply VIP discount
        double discount = isVIP ? (subtotal * VIP_DISCOUNT_RATE) : 0;
        double finalCost = subtotal - discount;

        // Display test result
        System.out.println(testName);
        System.out.println("  ปลายทาง: " + (isBangkokArea ? "กรุงเทพฯ" : "ต่างจังหวัด"));
        System.out.println("  น้ำหนัก: " + weight + " กก.");
        System.out.println("  บริการด่วน: " + (isExpress ? "ใช่" : "ไม่"));
        System.out.println("  สมาชิก VIP: " + (isVIP ? "ใช่" : "ไม่"));
        System.out.println("  ค่าขนส่งพื้นฐาน: " + String.format("%.2f", baseCost) + " บาท");
        if (isExpress) {
            System.out.println("  ค่าบริการด่วน: " + String.format("%.2f", expressFee) + " บาท");
        }
        System.out.println("  ยอดรวม: " + String.format("%.2f", subtotal) + " บาท");
        if (isVIP) {
            System.out.println("  ส่วนลด VIP: " + String.format("%.2f", discount) + " บาท");
        }
        System.out.println("  ยอดชำระสุทธิ: " + String.format("%.2f", finalCost) + " บาท");
        System.out.println("  ผลลัพธ์: " + (Math.abs(finalCost - expectedCost) < 0.01 ? "ผ่าน ✓" : "ไม่ผ่าน ✗"));
        System.out.println();
    }
}