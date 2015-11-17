var COMPUTER_START_BUTTON = "#aiStart";

var board;
var canStart = true;

function playerMove(cell, ai, boardUtils, callback) {
    if (boardUtils.markCell(cell, Tile.X)) {
        canStart = false;
        if (boardUtils.hasWon(Tile.X, board)) {
            callback(Tile.X);
        } else if (boardUtils.isTie(board)) {
            callback(Tile.BLANK);
        } else {

            var aiMove = ai.makeMove();
            boardUtils.markCell(aiMove, ai.getSign());

            if (boardUtils.hasWon(ai.getSign(), board)) {
                callback(ai.getSign());
            }

            if (boardUtils.isTie(board)) {
                callback(Tile.BLANK);
            }
        }
    }
}

(function Game() {
    var boardUtils = new BoardUtils();
    board = boardUtils.initBoard();

    var ai = new AiPlayer(board, Tile.O);

    $(boardUtils.getBoardId()).on("click", "td", function (event) {
        playerMove(event.target.id, ai, boardUtils, function (winner) {
            if (winner === Tile.X) {
                alert("Player Won!");
                restart();

            } else if (winner === Tile.O) {
                alert("AI Won!");
                restart();

            } else if (winner === Tile.BLANK) {
                alert("Draw!");
                restart()
            }
        });
    });

    $(COMPUTER_START_BUTTON).on("click", function () {
        if (canStart) {
            var aiMove = ai.makeMove();
            boardUtils.markCell(aiMove, ai.getSign());
            canStart = false;
        }
    });

    function restart() {
        canStart = true;
        for (var i = board.length; i--;) {
            board[i] = Tile.BLANK;
            boardUtils.markCell(i, Tile.BLANK);
        }
    };

})();
