function AiPlayer(gameBoard, sign) {

    var self = this;

    self.setSign = function (sign) {
        self.sign = sign;
        self.opponentSign = sign === Tile.O ? Tile.X : Tile.O;
    };

    self.gameBoard = gameBoard;
    self.setSign(sign);

    self.getSign = function () {
        return self.sign;
    };

    self.makeMove = function () {
        return miniMax(2, -Infinity, Infinity, self.sign)[1];
    };

    var miniMax = function (depth, alpha, beta, player) {
        var nextMoves = getNextMoves();
        var numberOfNextMoves = nextMoves.length;

        var best = player === self.sign ? alpha : beta;
        var current;
        var bestIndex = -1;
        var move;
        var i;

        if (numberOfNextMoves === 0 || depth === 0) {
            best = evaluate();
            return [best, bestIndex];
        }

        if (player === self.sign) {

            for (i = numberOfNextMoves; i--;) {
                move = nextMoves[i];
                self.gameBoard[move] = player;

                current = miniMax(depth - 1, alpha, beta, self.opponentSign)[0];
                if (alpha < current) {
                    alpha = current;
                    bestIndex = move;
                }

                self.gameBoard[move] = Tile.BLANK;

                if (alpha >= beta) {
                    break;
                }
            }

            return [alpha, bestIndex];

        } else if (player === self.opponentSign) {

            for (i = numberOfNextMoves; i--;) {
                move = nextMoves[i];
                self.gameBoard[move] = player;

                current = miniMax(depth - 1, alpha, beta, self.sign)[0];
                if (beta > current) {
                    beta = current;
                    bestIndex = move;
                }

                self.gameBoard[move] = Tile.BLANK;

                if (alpha >= beta) {
                    break;
                }
            }

            return [beta, bestIndex];
        }
    };

    var evaluate = function () {
        var sum = 0;

        sum += evaluateLine(0, 1, 2);
        sum += evaluateLine(3, 4, 5);
        sum += evaluateLine(6, 7, 8);
        sum += evaluateLine(0, 3, 6);
        sum += evaluateLine(1, 4, 7);
        sum += evaluateLine(2, 5, 8);
        sum += evaluateLine(0, 4, 8);
        sum += evaluateLine(2, 4, 6);

        return sum;
    };

    var evaluateLine = function (index1, index2, index3) {
        var sum = 0;

        if (self.gameBoard[index1] === self.sign) {
            sum = 1;
        } else if (self.gameBoard[index1] === self.opponentSign) {
            sum = -1;
        }

        if (self.gameBoard[index2] === self.sign) {
            if (sum === 1) {
                sum = 10;
            } else if (sum === -1) {
                return 0;
            } else {
                sum = 1;
            }
        } else if (self.gameBoard[index2] === self.opponentSign) {
            if (sum === -1) {
                sum = -10;
            } else if (sum === 1) {
                return 0;
            } else {
                sum = -1;
            }
        }

        if (self.gameBoard[index3] === self.sign) {
            if (sum > 0) {
                sum *= 10;
            } else if (sum < 0) {
                return 0;
            } else {
                sum = 1;
            }
        } else if (self.gameBoard[index3] === self.opponentSign) {
            if (sum < 0) {
                sum *= 10;
            } else if (sum > 0) {
                return 0;
            } else {
                sum = -1;
            }
        }

        return sum;
    };

    var getNextMoves = function () {
        var moves = [];
        var length = self.gameBoard.length;

        for (var i = length; i--;) {
            if (!self.gameBoard[i]) moves.push(i);
        }

        return moves;
    };
}