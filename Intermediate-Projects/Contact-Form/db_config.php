<?php
$host = "localhost";
$username = "root";
$password = ""; // Default is blank in XAMPP
$database = "contact_form_db";

// Create connection
$conn = new mysqli($host, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
