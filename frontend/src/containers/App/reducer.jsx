import * as con from "./constants";

const initState = {
    boardList: []
};

function putTasksToBoards(boardList, taskList) {
    let newBoardsList = [];
    newBoardsList = boardList.map(function (elementBoard, iBoard, arrBoard) {
        elementBoard.task = [];
        return elementBoard;
    });
    newBoardsList.forEach(function (elementBoard, iBoard, arrBoard) {
        taskList.forEach((element, i, arr) => {
            if (element.boardId === elementBoard.id) {
                elementBoard.task.push(element);
            }
        });
    });
    return newBoardsList;
}

function exstractAllTasks(boardList) {
    let allTasks = [];
    boardList.forEach(function (elementBoard, iBoard, arrBoard) {
        elementBoard.task.forEach((task, iTask, arrTask) => {
            allTasks.push(task);
        });
    });

    return allTasks;
}

function updateTaskInBoard(boardList, newTask) {
    boardList.forEach(function (elementBoard, iBoard, arrBoard) {
        if (newTask.boardId === elementBoard.id) {
            elementBoard.task.forEach((oldTask, iTask, arrTask) => {
                if (newTask.id === oldTask.id) {
                    arrTask[iTask] = newTask;
                }
            });
        }
    });
}

const BoardReducer = (state = initState, action) => {
    let taskList;
    let newTask;
    let boardList = state.boardList;
    switch (action.type) {
        case con.FETCH_BOARD_LIST_SUCCESS:
            return {
                boardList: action.boarList
            };
        case con.CREATE_BOARD_SUCCESS:
            let newBoard = action.newBoard;
            boardList.push(newBoard);
            return {
                boardList: boardList
            };
        case con.DELETE_BOARD_SUCCESS:
            let boardId = action.boardId;
            boardList = boardList.filter((elementBoard) => {
                if (elementBoard.id !== boardId) {
                    return true;
                }
            });
            return {
                boardList: boardList
            };
        case con.FETCH_TASK_LIST_SUCCESS:
            taskList = action.taskList;
            return {
                boardList: putTasksToBoards(boardList, taskList)
            };
        case con.CREATE_TASK_SUCCESS:
            newTask = action.newTask;
            boardList.forEach(function (elementBoard, iBoard, arrBoard) {
                if (newTask.boardId === elementBoard.id) {
                    elementBoard.task.push(newTask);
                }
            });
            return {
                boardList: boardList
            };
        case con.UPDATE_TASK_SUCCESS:
            newTask = action.newTask;
            updateTaskInBoard(boardList, newTask);
            let allTask = exstractAllTasks(boardList);
            boardList = putTasksToBoards(boardList, allTask);
            return {
                boardList: boardList
            };
        case con.DELETE_TASK_SUCCESS:
            let taskId = action.taskId;
            boardList.forEach((elementBoard, iBoard, arrBoard) => {
                elementBoard.task = elementBoard.task.filter((taskElement) => {
                    if (taskElement.id !== taskId) {
                        return true;
                    }
                });
            });
            return {
                boardList: boardList
            };
        default:
            return state
    }
};

export default BoardReducer