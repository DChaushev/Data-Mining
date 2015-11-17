function AiPlayer(boardUtils, sign) {
    "use strict";

    var self = this;

    self.setSign = function (sign) {
        self.sign = sign;
        self.opponentSign = sign === Tile.O ? Tile.X : Tile.O;
    };

    self.boardUtils = boardUtils;
    self.gameBoard = boardUtils.getGameBoard();
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

        var best = (player === self.sign) ? alpha : beta;
        var score;
        var bestMove = -1;
        var i;

        if (numberOfNextMoves === 0 || depth === 0) {
            best = evaluate();
            return [best, bestMove];
        } else {
            for (i = numberOfNextMoves; i--;) {
                var move = nextMoves[i];
                self.gameBoard[move] = player;

                if (player === self.sign) {
                    score = miniMax(depth - 1, alpha, beta, self.opponentSign)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestMove = move;
                    }
                } else {
                    score = miniMax(depth - 1, alpha, beta, self.sign)[0];
                    if (score < beta) {
                        beta = score;
                        bestMove = move;
                    }
                }
                self.gameBoard[move] = Tile.BLANK;
                if (alpha >= beta) {
                    break;
                }
            }
            return [player == self.sign ? alpha : beta, bestMove];
        }
    };

    var evaluate = function () {
        var score = 0;

        score += evaluateLine(0, 1, 2);
        score += evaluateLine(3, 4, 5);
        score += evaluateLine(6, 7, 8);
        score += evaluateLine(0, 3, 6);
        score += evaluateLine(1, 4, 7);
        score += evaluateLine(2, 5, 8);
        score += evaluateLine(0, 4, 8);
        score += evaluateLine(2, 4, 6);

        return score;
    };

    var evaluateLine = function (index1, index2, index3) {
        var score = 0;

        if (self.gameBoard[index1] === self.sign) {
            score = 1;
        } else if (self.gameBoard[index1] === self.opponentSign) {
            score = -1;
        }

        if (self.gameBoard[index2] === self.sign) {
            if (score === 1) {
                score = 10;
            } else if (score === -1) {
                return 0;
            } else {
                score = 1;
            }
        } else if (self.gameBoard[index2] === self.opponentSign) {
            if (score === -1) {
                score = -10;
            } else if (score === 1) {
                return 0;
            } else {
                score = -1;
            }
        }

        if (self.gameBoard[index3] === self.sign) {
            if (score > 0) {
                score *= 10;
            } else if (score < 0) {
                return 0;
            } else {
                score = 1;
            }
        } else if (self.gameBoard[index3] === self.opponentSign) {
            if (score < 0) {
                score *= 10;
            } else if (score > 0) {
                return 0;
            } else {
                score = -1;
            }
        }

        return score;
    };

    var getNextMoves = function () {
        var moves = [];

        if (self.boardUtils.hasWon(self.sign, self.gameBoard) || self.boardUtils.hasWon(self.opponentSign, self.gameBoard)) {
            return moves;
        }

        var length = self.gameBoard.length;

        for (var i = length; i--;) {
            if (!self.gameBoard[i]) moves.push(i);
        }

        return moves;
    };
}