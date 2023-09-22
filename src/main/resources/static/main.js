// Define WebSocket URL with the chat room ID
const chatId = "123"; // Replace with the dynamic chat ID from user input
const socket = new SockJS(`/websocket`);

// Initialize STOMP client
const stompClient = Stomp.over(socket);

// Get DOM elements
const messageInput = document.getElementById("message-input");
const messageDisplay = document.getElementById("message-display");

// Function to append a message to the display
function appendMessage(message) {
    const messageElement = document.createElement("div");
    messageElement.innerText = message;
    messageDisplay.appendChild(messageElement);
}

// Function to send a message via WebSocket
function sendMessage() {
    const message = messageInput.value;
    if (message) {
        stompClient.send(`/app/chat.sendMessage`, {}, JSON.stringify({ content: message }));
        messageInput.value = "";
    }
}

// Handle incoming messages from the WebSocket
socket.onmessage = (event) => {
    const message = event.data;
    appendMessage(message);
};

// Subscribe to WebSocket connection open event
socket.onopen = () => {
    console.log("WebSocket connection opened.");
};

// Subscribe to WebSocket connection close event
socket.onclose = (event) => {
    if (event.wasClean) {
        console.log(`WebSocket connection closed cleanly, code=${event.code}, reason=${event.reason}`);
    } else {
        console.error("WebSocket connection abruptly closed.");
    }
};

// Subscribe to WebSocket connection error event
socket.onerror = (error) => {
    console.error(`WebSocket connection error: ${error.message}`);
};
