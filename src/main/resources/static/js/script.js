// Function to create a new project
function createProject() {
    const projectName = document.getElementById('projectName').value;

    fetch('/api/admin/project/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({ projectName })
    })
        .then(response => response.json())
        .then(data => {
            alert('Project Created: ' + data.projectName);
        })
        .catch(error => console.error('Error:', error));
}

// Function to fetch projects for a manager
function getProjectsForManager() {
    const managerId = document.getElementById('managerId').value;

    fetch(`/api/admin/project/manager/${managerId}`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            const projectsDiv = document.getElementById('projects');
            projectsDiv.innerHTML = '';

            data.forEach(project => {
                const projectElement = document.createElement('div');
                projectElement.textContent = `Project: ${project.projectName}, Manager: ${project.managerId}`;
                projectsDiv.appendChild(projectElement);
            });
        })
        .catch(error => console.error('Error:', error));
}
