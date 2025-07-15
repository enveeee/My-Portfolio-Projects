<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Include DB config file
    include 'db_config.php';

    // Collect and sanitize input
    $name = htmlspecialchars(trim($_POST['name']));
    $email = htmlspecialchars(trim($_POST['email']));
    $subject = htmlspecialchars(trim($_POST['subject']));
    $message = htmlspecialchars(trim($_POST['message']));

    // Insert into database
    $sql = "INSERT INTO messages (name, email, subject, message) 
            VALUES (?, ?, ?, ?)";

    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ssss", $name, $email, $subject, $message);

    if ($stmt->execute()) {
        echo "<script>
                alert('✅ Message Sent Successfully!');
                window.location.href = 'index.html';
              </script>";
    } else {
        echo "<script>
                alert('❌ Something went wrong. Please try again.');
                window.location.href = 'index.html';
              </script>";
    }

    // Close
    $stmt->close();
    $conn->close();
} else {
    echo "<script>
            alert('Invalid Request');
            window.location.href = 'index.html';
          </script>";
}
?>
