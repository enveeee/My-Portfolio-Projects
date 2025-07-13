document.getElementById("gradeForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const total = parseFloat(document.getElementById("totalMarks").value);
  const obtained = parseFloat(document.getElementById("obtainedMarks").value);

  const resultBox = document.getElementById("resultBox");
  const percentageText = document.getElementById("percentageText");
  const gradeText = document.getElementById("gradeText");
  const fill = document.getElementById("progressFill");

  if (total <= 0 || obtained < 0 || obtained > total) {
    alert("❌ Please enter valid marks.");
    return;
  }

  const percentage = (obtained / total) * 100;
  let grade = "";

  if (percentage >= 90) grade = "A+ (Excellent)";
  else if (percentage >= 80) grade = "A (Very Good)";
  else if (percentage >= 70) grade = "B (Good)";
  else if (percentage >= 60) grade = "C (Average)";
  else if (percentage >= 50) grade = "D (Poor)";
  else grade = "F (Fail)";

  percentageText.textContent = `📊 Your Percentage: ${percentage.toFixed(2)}%`;
  gradeText.textContent = `🏅 Grade: ${grade}`;
  fill.style.width = percentage + "%";

  resultBox.style.display = "block";
});
