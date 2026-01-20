document.addEventListener("DOMContentLoaded", () => {
  // DOM elements
  const gameBoard = document.getElementById("game-board");
  const moveCountElement = document.getElementById("move-count");
  const timerElement = document.getElementById("timer");
  const restartBtn = document.getElementById("restart-btn");
  const winMessage = document.getElementById("win-message");
  const finalMovesElement = document.getElementById("final-moves");
  const finalTimeElement = document.getElementById("final-time");
  const playAgainBtn = document.getElementById("play-again-btn");

  // Game state
  let cards = [];
  let flippedCards = [];
  let matchedPairs = 0;
  let moves = 0;
  let timer = 0;
  let timerInterval = null;
  let gameStarted = false;

  // Card icons (emoji) - 8 pairs for a 4x4 grid
  const cardIcons = ["🍎", "🍌", "🍒", "🍇", "🍊", "🍓", "🍑", "🍍"];

  // Initialize game
  function initGame() {
    // Reset game state
    gameBoard.innerHTML = "";
    flippedCards = [];
    matchedPairs = 0;
    moves = 0;
    timer = 0;
    moveCountElement.textContent = moves;
    timerElement.textContent = timer;

    // Clear any existing timer
    if (timerInterval) {
      clearInterval(timerInterval);
    }

    // Hide win message
    winMessage.classList.remove("show");

    // Create card pairs
    let gameCards = [...cardIcons, ...cardIcons];

    // Shuffle cards
    shuffleArray(gameCards);

    // Create card elements
    gameCards.forEach((icon, index) => {
      const card = document.createElement("div");
      card.className = "card";
      card.dataset.icon = icon;
      card.dataset.index = index;

      const cardFront = document.createElement("div");
      cardFront.className = "card-face card-front";
      cardFront.textContent = icon;

      const cardBack = document.createElement("div");
      cardBack.className = "card-face card-back";

      card.appendChild(cardFront);
      card.appendChild(cardBack);

      card.addEventListener("click", flipCard);
      gameBoard.appendChild(card);
    });

    // Start timer when first card is clicked
    gameStarted = false;
  }

  // Shuffle array using Fisher-Yates algorithm
  function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  // Flip card function
  function flipCard() {
    // Don't allow flipping if two cards are already flipped or card is already matched
    if (
      flippedCards.length === 2 ||
      this.classList.contains("matched") ||
      this.classList.contains("flipped")
    ) {
      return;
    }

    // Start timer on first move
    if (!gameStarted) {
      startTimer();
      gameStarted = true;
    }

    // Flip the card
    this.classList.add("flipped");
    flippedCards.push(this);

    // Check for match when two cards are flipped
    if (flippedCards.length === 2) {
      moves++;
      moveCountElement.textContent = moves;

      setTimeout(checkMatch, 500);
    }
  }

  // Check if flipped cards match
  function checkMatch() {
    const [card1, card2] = flippedCards;
    const isMatch = card1.dataset.icon === card2.dataset.icon;

    if (isMatch) {
      // Cards match
      card1.classList.add("matched");
      card2.classList.add("matched");
      matchedPairs++;

      // Check for win
      if (matchedPairs === cardIcons.length) {
        endGame();
      }
    } else {
      // Cards don't match, flip them back
      card1.classList.remove("flipped");
      card2.classList.remove("flipped");
    }

    // Reset flipped cards array
    flippedCards = [];
  }

  // Start the game timer
  function startTimer() {
    timerInterval = setInterval(() => {
      timer++;
      timerElement.textContent = timer;
    }, 1000);
  }

  // End the game
  function endGame() {
    clearInterval(timerInterval);

    // Show win message
    finalMovesElement.textContent = moves;
    finalTimeElement.textContent = timer;
    winMessage.classList.add("show");
  }

  // Event listeners
  restartBtn.addEventListener("click", initGame);
  playAgainBtn.addEventListener("click", () => {
    winMessage.classList.remove("show");
    initGame();
  });

  // Initialize the game
  initGame();
});
