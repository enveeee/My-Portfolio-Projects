# bmi_calculator.py

def calculate_bmi(weight, height_cm):
    height_m = height_cm / 100  # convert cm to meters
    bmi = weight / (height_m ** 2)
    return round(bmi, 2)

def classify_bmi(bmi):
    if bmi < 18.5:
        return "Underweight 😟"
    elif 18.5 <= bmi < 24.9:
        return "Normal Weight ✅"
    elif 25 <= bmi < 29.9:
        return "Overweight ⚠️"
    else:
        return "Obese 🚨"

def main():
    print("🧮 BMI Calculator")
    print("===================")
    try:
        weight = float(input("Enter your weight in kilograms (e.g. 65.5): "))
        height = float(input("Enter your height in centimeters (e.g. 170): "))
        
        bmi = calculate_bmi(weight, height)
        category = classify_bmi(bmi)
        
        print(f"\nYour BMI is: {bmi}")
        print(f"You are classified as: {category}")
    
    except ValueError:
        print("❌ Invalid input! Please enter numeric values only.")

if __name__ == "__main__":
    main()
