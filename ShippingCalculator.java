import java.util.Scanner;

public class ShippingCalculator {

    // Pricing constants for Bangkok and surrounding areas
    private static final double BANGKOK_WEIGHT_0_1_KG = 40.0;
    private static final double BANGKOK_WEIGHT_1_3_KG = 60.0;
    private static final double BANGKOK_WEIGHT_3_5_KG = 80.0;
    private static final double BANGKOK_WEIGHT_ABOVE_5_KG = 100.0;

    // Pricing constants for other provinces
    private static final double PROVINCE_WEIGHT_0_1_KG = 60.0;
    private static final double PROVINCE_WEIGHT_1_3_KG = 90.0;
    private static final double PROVINCE_WEIGHT_3_5_KG = 120.0;
    private static final double PROVINCE_WEIGHT_ABOVE_5_KG = 150.0;

    // Additional service charges
    private static final double EXPRESS_SERVICE_FEE = 30.0;
    private static final double VIP_DISCOUNT_RATE = 0.20; // 20% discount

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ระบบคำนวณค่าจัดส่งสินค้า ===");
        System.out.println();

        // Get destination
        System.out.println("เลือกปลายทาง:");
        System.out.println("1. กรุงเทพฯ และปริมณฑล");
        System.out.println("2. ต่างจังหวัด");
        System.out.print("กรุณาเลือก (1 หรือ 2): ");
        int destinationChoice = scanner.nextInt();

        boolean isBangkokArea = (destinationChoice == 1);

        // Get weight
        System.out.print("กรุณาใส่น้ำหนักสินค้า (กิโลกรัม): ");
        double weight = scanner.nextDouble();

        // Validate weight
        if (weight <= 0) {
            System.out.println("น้ำหนักต้องมากกว่า 0");
            scanner.close();
            return;
        }

        // Get express service option
        System.out.print("เลือกบริการด่วน? (1=ใช่, 0=ไม่): ");
        int expressChoice = scanner.nextInt();
        boolean isExpress = (expressChoice == 1);

        // Get VIP status
        System.out.print("เป็นสมาชิก VIP? (1=ใช่, 0=ไม่): ");
        int vipChoice = scanner.nextInt();
        boolean isVIP = (vipChoice == 1);

        // Calculate shipping cost
        double baseCost = calculateBaseCost(isBangkokArea, weight);
        double expressFee = isExpress ? EXPRESS_SERVICE_FEE : 0;
        double subtotal = baseCost + expressFee;
        double discount = isVIP ? (subtotal * VIP_DISCOUNT_RATE) : 0;
        double finalCost = subtotal - discount;

        // Display results
        System.out.println("\n=== สรุปค่าจัดส่ง ===");
        System.out.println("ปลายทาง: " + (isBangkokArea ? "กรุงเทพฯ และปริมณฑล" : "ต่างจังหวัด"));
        System.out.println("น้ำหนัก: " + weight + " กิโลกรัม");
        System.out.println("ค่าขนส่งพื้นฐาน: " + String.format("%.2f", baseCost) + " บาท");

        if (isExpress) {
            System.out.println("ค่าบริการด่วน: " + String.format("%.2f", expressFee) + " บาท");
        }

        System.out.println("รวมเป็นเงิน: " + String.format("%.2f", subtotal) + " บาท");

        if (isVIP) {
            System.out.println("ส่วนลดสมาชิก VIP (20%): " + String.format("%.2f", discount) + " บาท");
        }

        System.out.println("ยอดชำระสุทธิ: " + String.format("%.2f", finalCost) + " บาท");

        scanner.close();
    }

    /**
     * Calculate base shipping cost based on destination and weight
     */
    private static double calculateBaseCost(boolean isBangkokArea, double weight) {
        if (isBangkokArea) {
            // Bangkok and surrounding areas pricing
            if (weight <= 1) {
                return BANGKOK_WEIGHT_0_1_KG;
            } else if (weight <= 3) {
                return BANGKOK_WEIGHT_1_3_KG;
            } else if (weight <= 5) {
                return BANGKOK_WEIGHT_3_5_KG;
            } else {
                return BANGKOK_WEIGHT_ABOVE_5_KG;
            }
        } else {
            // Other provinces pricing
            if (weight <= 1) {
                return PROVINCE_WEIGHT_0_1_KG;
            } else if (weight <= 3) {
                return PROVINCE_WEIGHT_1_3_KG;
            } else if (weight <= 5) {
                return PROVINCE_WEIGHT_3_5_KG;
            } else {
                return PROVINCE_WEIGHT_ABOVE_5_KG;
            }
        }
    }
}