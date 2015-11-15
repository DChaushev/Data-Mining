function BoardUtils() {

    var NUMBER_OF_CELLS = 3;

    var BOARD = "#board";

    var NEW_ROW_FORMAT = "<tr><td id='{0}'></td><td id='{1}'></td><td id='{2}'></td></tr>";
    var CELL_ID_FORMAT = "#{0}";

    this.board = [];

    this.initBoard = function () {
        var cell = -1;
        for (var i = 0; i < NUMBER_OF_CELLS; i += 1) {

            this.board.push(Tile.BLANK);
            this.board.push(Tile.BLANK);
            this.board.push(Tile.BLANK);

            $(BOARD).append(
                String.format(NEW_ROW_FORMAT, ++cell, ++cell, ++cell)
            );
        }

        return this.board;
    };

    this.getBoardId = function () {
        return BOARD;
    };

    this.markCell = function (cellIndex, tile) {
        if (!this.board[cellIndex]) {
            var cell = String.format(CELL_ID_FORMAT, cellIndex);
            var color = tile === Tile.X ? Color.BLUE : Color.RED;

            $(cell).text(tile);
            $(cell).css('color', color);

            this.board[cellIndex] = tile;
            console.log(this.board);
            return true;
        }

        return false;
    };

    var winningPatterns = (function () {
        var patterns = [
            "111000000", "000111000", "000000111",
            "100100100", "010010010", "001001001",
            "100010001", "001010100"];

        var length = patterns.length;
        var result = new Array(length);

        for (var i = length; i--;) {
            result[i] = parseInt(patterns[i], 2);
        }

        return result;
    })();

    this.hasWon = function (player, board) {
        var pattern = 0;
        var length = board.length;
        var i;

        for (i = length; i--;) {
            if (board[i] === player) {
                pattern |= (1 << i);
            }
        }

        length = winningPatterns.length;
        for (i = length; i--;) {
            if ((pattern & winningPatterns[i]) === winningPatterns[i]) return true;
        }
        return false;
    };

    this.isTie = function (board) {
        for (var i = board.length; i--;) {
            if (board[i] === Tile.BLANK)
                return false;
        }
        return true;
    };
}