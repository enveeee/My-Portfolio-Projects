<?php
include 'db_config.php';

$sql = "SELECT * FROM messages ORDER BY submitted_at DESC";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin - View Messages</title>
  <link rel="stylesheet" href="style.css" />
  <style>
    .admin-container {
      background-color: #ffffff;
      padding: 30px;
      margin: 30px auto;
      border-radius: 20px;
      box-shadow: 0 5px 20px rgba(180, 148, 255, 0.2);
      max-width: 95%;
      overflow-x: auto;
    }

    h2 {
      text-align: center;
      color: #5a4b81;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 25px;
    }

    table th, table td {
      padding: 12px;
      text-align: left;
      border-bottom: 1px solid #eee;
    }

    th {
      background-color: #f3e8ff;
      color: #5a4b81;
    }

    tr:hover {
      background-color: #f9f5ff;
    }

    @media (max-width: 600px) {
      table {
        font-size: 14px;
      }
    }
  </style>
</head>
<body>
  <div class="admin-container">
    <h2>📨 Submitted Messages</h2>

    <?php if ($result->num_rows > 0): ?>
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Subject</th>
          <th>Message</th>
          <th>Date</th>
        </tr>
      </thead>
      <tbody>
        <?php while($row = $result->fetch_assoc()): ?>
        <tr>
          <td><?php echo htmlspecialchars($row["name"]); ?></td>
          <td><?php echo htmlspecialchars($row["email"]); ?></td>
          <td><?php echo htmlspecialchars($row["subject"]); ?></td>
          <td><?php echo htmlspecialchars($row["message"]); ?></td>
          <td><?php echo $row["submitted_at"]; ?></td>
        </tr>
        <?php endwhile; ?>
      </tbody>
    </table>
    <?php else: ?>
      <p>No messages found.</p>
    <?php endif; ?>

  </div>
</body>
</html>

<?php $conn->close(); ?>
