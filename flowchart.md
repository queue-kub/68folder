# Shipping Calculator Flowchart and Pseudocode

## Flowchart

```mermaid
flowchart TD
    A[Start] --> B[Initialize Scanner]
    B --> C[Display welcome message]
    C --> D[Get destination choice<br/>1=Bangkok area, 2=Province]
    D --> E[Store isBangkokArea flag]
    E --> F[Get weight input]
    F --> G[Validate weight > 0<br/>if not, exit with error]
    G --> H[Get express service choice<br/>1=Yes, 0=No]
    H --> I[Store isExpress flag]
    I --> J[Get VIP status choice<br/>1=Yes, 0=No]
    J --> K[Store isVIP flag]
    K --> L[Calculate base cost using<br/>calculateBaseCost function]
    L --> M[Calculate express fee<br/>if isExpress then 30, else 0]
    M --> N[Calculate subtotal<br/>baseCost + expressFee]
    N --> O[Calculate discount<br/>if isVIP then subtotal * 0.2, else 0]
    O --> P[Calculate final cost<br/>subtotal - discount]
    P --> Q[Display cost breakdown]
    Q --> R[Close Scanner]
    R --> S[End]

    L --> L1[Check if Bangkok area?]
    L1 -->|Yes| L2[Weight ≤ 1kg?]
    L1 -->|No| L3[Weight ≤ 1kg?]
    L2 -->|Yes| L4[Return 40.0]
    L2 -->|No| L5[Weight ≤ 3kg?]
    L5 -->|Yes| L6[Return 60.0]
    L5 -->|No| L7[Weight ≤ 5kg?]
    L7 -->|Yes| L8[Return 80.0]
    L7 -->|No| L9[Return 100.0]
    L3 -->|Yes| L10[Return 60.0]
    L3 -->|No| L11[Weight ≤ 3kg?]
    L11 -->|Yes| L12[Return 90.0]
    L11 -->|No| L13[Weight ≤ 5kg?]
    L13 -->|Yes| L14[Return 120.0]
    L13 -->|No| L15[Return 150.0]
```

## Pseudocode

```
BEGIN ShippingCalculator

    // Define pricing constants
    CONSTANT BANGKOK_WEIGHT_0_1_KG = 40.0
    CONSTANT BANGKOK_WEIGHT_1_3_KG = 60.0
    CONSTANT BANGKOK_WEIGHT_3_5_KG = 80.0
    CONSTANT BANGKOK_WEIGHT_ABOVE_5_KG = 100.0
    
    CONSTANT PROVINCE_WEIGHT_0_1_KG = 60.0
    CONSTANT PROVINCE_WEIGHT_1_3_KG = 90.0
    CONSTANT PROVINCE_WEIGHT_3_5_KG = 120.0
    CONSTANT PROVINCE_WEIGHT_ABOVE_5_KG = 150.0
    
    CONSTANT EXPRESS_SERVICE_FEE = 30.0
    CONSTANT VIP_DISCOUNT_RATE = 0.20

    MAIN()
        CREATE Scanner object
        
        DISPLAY "=== ระบบคำนวณค่าจัดส่งสินค้า ==="
        
        DISPLAY "เลือกปลายทาง:"
        DISPLAY "1. กรุงเทพฯ และปริมณฑล"
        DISPLAY "2. ต่างจังหวัด"
        INPUT destinationChoice
        
        SET isBangkokArea = (destinationChoice == 1)
        
        INPUT weight
        
        IF weight <= 0 THEN
            DISPLAY "น้ำหนักต้องมากกว่า 0"
            CLOSE scanner
            EXIT
        END IF
        
        INPUT expressChoice
        SET isExpress = (expressChoice == 1)
        
        INPUT vipChoice
        SET isVIP = (vipChoice == 1)
        
        SET baseCost = calculateBaseCost(isBangkokArea, weight)
        SET expressFee = IF isExpress THEN EXPRESS_SERVICE_FEE ELSE 0
        SET subtotal = baseCost + expressFee
        SET discount = IF isVIP THEN (subtotal * VIP_DISCOUNT_RATE) ELSE 0
        SET finalCost = subtotal - discount
        
        DISPLAY "=== สรุปค่าจัดส่ง ==="
        DISPLAY "ปลายทาง: " + IF isBangkokArea THEN "กรุงเทพฯ และปริมณฑล" ELSE "ต่างจังหวัด"
        DISPLAY "น้ำหนัก: " + weight + " กิโลกรัม"
        DISPLAY "ค่าขนส่งพื้นฐาน: " + FORMAT(baseCost) + " บาท"
        
        IF isExpress THEN
            DISPLAY "ค่าบริการด่วน: " + FORMAT(expressFee) + " บาท"
        END IF
        
        DISPLAY "รวมเป็นเงิน: " + FORMAT(subtotal) + " บาท"
        
        IF isVIP THEN
            DISPLAY "ส่วนลดสมาชิก VIP (20%): " + FORMAT(discount) + " บาท"
        END IF
        
        DISPLAY "ยอดชำระสุทธิ: " + FORMAT(finalCost) + " บาท"
        
        CLOSE scanner
    END MAIN

    FUNCTION calculateBaseCost(isBangkokArea, weight)
        IF isBangkokArea THEN
            IF weight <= 1 THEN
                RETURN BANGKOK_WEIGHT_0_1_KG
            ELSE IF weight <= 3 THEN
                RETURN BANGKOK_WEIGHT_1_3_KG
            ELSE IF weight <= 5 THEN
                RETURN BANGKOK_WEIGHT_3_5_KG
            ELSE
                RETURN BANGKOK_WEIGHT_ABOVE_5_KG
            END IF
        ELSE
            IF weight <= 1 THEN
                RETURN PROVINCE_WEIGHT_0_1_KG
            ELSE IF weight <= 3 THEN
                RETURN PROVINCE_WEIGHT_1_3_KG
            ELSE IF weight <= 5 THEN
                RETURN PROVINCE_WEIGHT_3_5_KG
            ELSE
                RETURN PROVINCE_WEIGHT_ABOVE_5_KG
            END IF
        END IF
    END FUNCTION

END ShippingCalculator
```