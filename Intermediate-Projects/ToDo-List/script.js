let tasks = [];

document.getElementById("theme-toggle").addEventListener("change", function () {
  document.body.classList.toggle("dark");
});

function addTask() {
  const input = document.getElementById("task-input");
  const category = document.getElementById("category-select").value;
  const text = input.value.trim();

  if (text !== "") {
    tasks.push({ text, completed: false, category });
    input.value = "";
    renderTasks();
  }
}

function toggleTask(index) {
  tasks[index].completed = !tasks[index].completed;
  renderTasks();
}

function deleteTask(index) {
  tasks.splice(index, 1);
  renderTasks();
}

function filterTasks(type) {
  renderTasks(type);
}

function renderTasks(filter = "all") {
  const taskList = document.getElementById("task-list");
  taskList.innerHTML = "";

  let filtered = tasks;
  if (filter === "completed") {
    filtered = tasks.filter((t) => t.completed);
  } else if (filter === "active") {
    filtered = tasks.filter((t) => !t.completed);
  }

  filtered.forEach((task, index) => {
    const li = document.createElement("li");
    li.className = task.completed ? "completed" : "";

    li.innerHTML = `
      <span onclick="toggleTask(${index})">${task.text} <em>(${task.category})</em></span>
      <button onclick="deleteTask(${index})">❌</button>
    `;

    taskList.appendChild(li);
  });
}
