const messagesContainer = document.getElementById('messages');
let socket;

function createWebSocket(port, userName) {
    const ws = new WebSocket(`ws://localhost:${port}/ws`);

    ws.onopen = () => {
        console.log('Connected to WebSocket server');
        const joinMessage = {type: 'join', username: userName};
        ws.send(JSON.stringify(joinMessage));
    };

    ws.onmessage = (event) => {
        const data = JSON.parse(event.data);
        if (Array.isArray(data)) {
            // Это список участников
            updateParticipantsList(data);
        } else {
            // Это сообщение чата
            const {name, text, date} = data;
            if (name === 'Сервер' && text === 'Имя пользователя уже занято. Попробуйте другое имя.') {
                alert(text);
                logout();
            } else {
                const messageElement = document.createElement('div');
                messageElement.className = 'message ' + (name === userName ? 'own' : 'other');
                messageElement.textContent = `${name}: ${text} (${new Date(date).toLocaleString()})`;

                messagesContainer.appendChild(messageElement);
                messagesContainer.scrollTop = messagesContainer.scrollHeight;
            }
        }
    };

    ws.onclose = () => {
        console.error('WebSocket connection closed.');
    };

    ws.onerror = (error) => {
        console.error('WebSocket error:', error);
    };

    window.onbeforeunload = () => {
        if (ws.readyState === WebSocket.OPEN) {
            ws.close();
        }
    };

    return ws;
}

function updateParticipantsList(participants) {
    const participantsList = document.getElementById('participants');
    participantsList.innerHTML = '';
    participants.forEach(name => {
        const participantElement = document.createElement('li');
        participantElement.textContent = name;
        participantsList.appendChild(participantElement);
    });
}

let userName = getCookie('userName');
let serverPort = getCookie('serverPort');
if (userName && serverPort) {
    document.getElementById('loginContainer').style.display = 'none';
    document.getElementById('chatContainer').style.display = 'flex';
    document.getElementById('userNameDisplay').textContent = userName;
    socket = createWebSocket(serverPort, userName);
    loadMessages();
}

function login() {
    const inputName = document.getElementById('userNameInput');
    const inputPort = document.getElementById('portInput');
    const name = inputName.value.trim();
    const port = inputPort.value.trim();

    if (name && port) {
        userName = name;
        serverPort = port;
        setCookie('userName', name, 10); // Сохранение имени пользователя в cookies на 10 минут
        setCookie('serverPort', port, 10); // Сохранение порта сервера в cookies на 10 минут
        document.getElementById('loginContainer').style.display = 'none';
        document.getElementById('chatContainer').style.display = 'flex';
        document.getElementById('userNameDisplay').textContent = userName;
        socket = createWebSocket(serverPort, userName);
        loadMessages();
    }
}

function logout() {
    if (socket) {
        socket.close();
    }
    deleteCookie('userName');
    deleteCookie('serverPort');
    document.getElementById('loginContainer').style.display = 'flex';
    document.getElementById('chatContainer').style.display = 'none';
}

// Fetch last 20 messages when the app starts
function loadMessages() {
    fetch(`http://localhost:${serverPort}/messages?offset=0&amount=20`) // Обновите URL вашего REST API
        .then(response => response.json())
        .then(messages => {
            messagesContainer.innerHTML = '';
            messages.forEach(msg => {
                const messageElement = document.createElement('div');
                messageElement.className = 'message ' + (msg.name === userName ? 'own' : 'other');
                messageElement.textContent = `${msg.name}: ${msg.text} (${new Date(msg.date).toLocaleString()})`;
                messagesContainer.appendChild(messageElement);
            });
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
        })
        .catch(error => console.error('Error fetching messages:', error));
}

function sendMessage() {
    const input = document.getElementById('messageInput');
    const messageText = input.value.trim();

    if (messageText) {
        const message = {name: userName, text: messageText, date: new Date().toISOString()};
        socket.send(JSON.stringify(message));
        input.value = '';
    }
}

document.getElementById('messageInput').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
});

function setCookie(name, value, minutes) {
    const expires = new Date(Date.now() + minutes * 60000).toUTCString();
    document.cookie = `${name}=${value}; expires=${expires}; path=/`;
}

function getCookie(name) {
    const cookies = document.cookie.split('; ');
    for (const cookie of cookies) {
        const [key, value] = cookie.split('=');
        if (key === name) return value;
    }
    return null;
}

function deleteCookie(name) {
    document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
}
