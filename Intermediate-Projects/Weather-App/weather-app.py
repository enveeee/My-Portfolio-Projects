import requests
import tkinter as tk
from tkinter import messagebox

# Replace this with your real API key
API_KEY = "474d70eb049e5f65c1a59e5be5d3cad1"
BASE_URL = "https://api.openweathermap.org/data/2.5/weather"

# Fetch weather data
def get_weather():
    city = city_entry.get()
    if not city:
        messagebox.showwarning("Input Error", "Please enter a city name")
        return

    params = {
        'q': city,
        'appid': API_KEY,
        'units': 'metric'
    }

    try:
        response = requests.get(BASE_URL, params=params)
        data = response.json()

        if data["cod"] != 200:
            messagebox.showerror("Error", data["message"].capitalize())
        else:
            city_name = data["name"]
            temperature = data["main"]["temp"]
            humidity = data["main"]["humidity"]
            wind_speed = data["wind"]["speed"]
            condition = data["weather"][0]["description"].capitalize()

            result = f"""
📍 City: {city_name}
🌡 Temperature: {temperature} °C
💧 Humidity: {humidity} %
🌬 Wind Speed: {wind_speed} m/s
🌥 Condition: {condition}
"""
            result_label.config(text=result.strip())

    except Exception as e:
        messagebox.showerror("Error", f"Something went wrong:\n{e}")

# GUI setup
root = tk.Tk()
root.title("🌤️ Weather App")
root.geometry("400x400")
root.config(bg="#f5eaff")  # Pastel lavender

# Fonts & colors
FONT = ("Poppins", 12)
HEADING = ("Poppins", 16, "bold")

# Heading
tk.Label(root, text="Weather App", font=HEADING, bg="#f5eaff", fg="#8e44ad").pack(pady=10)

# Entry field
city_entry = tk.Entry(root, font=FONT, width=25, justify='center', bg="#fff8ff")
city_entry.pack(pady=10)
city_entry.insert(0, "Enter city name")

# Search button
tk.Button(root, text="Check Weather", font=FONT, command=get_weather, bg="#cda8f7", fg="white").pack(pady=5)

# Result label
result_label = tk.Label(root, font=FONT, bg="#f5eaff", justify="left", anchor="w")
result_label.pack(padx=20, pady=20, fill="both")

# Footer
tk.Label(root, text="Made with 💜 by Sanskruti", font=("Poppins", 9), bg="#f5eaff", fg="#9b59b6").pack(side="bottom", pady=10)

root.mainloop()
