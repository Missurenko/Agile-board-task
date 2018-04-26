import * as con from "./constants";
import $ from 'jquery';

export function getBoardList() {
    return dispatch => {
        $.get('/api/board', function (retData) {
            let boarList = retData;
            boarList = boarList.map((e) => {
                e.task = [];
                return e;
            });
            dispatch({
                type: con.FETCH_BOARD_LIST_SUCCESS,
                boarList: boarList
            })
        });
    }
}

export function createBoard(boardObject) {
    return dispatch => {
        $.ajax({
            type: "POST",
            url: "/api/board",
            dataType: 'json',
            data: JSON.stringify(boardObject),
            contentType: 'application/json'
        }).done(function (data, textStatus, jqXHR) {
            dispatch({
                type: con.CREATE_BOARD_SUCCESS,
                newBoard: data
            });
        });
    }
}

export function deleteBoard(id) {
    return dispatch => {
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id
        }).done(function (data, textStatus, jqXHR) {
            dispatch({
                type: con.DELETE_BOARD_SUCCESS,
                boardId: id
            });
        });
    }
}

export function getTaskList() {
    return dispatch => {
        $.get('/api/task', function (retData) {
            let taskList = retData;
            dispatch({
                type: con.FETCH_TASK_LIST_SUCCESS,
                taskList: taskList
            })
        });
    }
}

export function addTaskToBoard(taskObject) {
    return dispatch => {
        $.ajax({
            type: "POST",
            url: "/api/task",
            dataType: 'json',
            data: JSON.stringify(taskObject),
            contentType: 'application/json'
        }).done(function (data, textStatus, jqXHR) {
            dispatch({
                type: con.CREATE_TASK_SUCCESS,
                newTask: data
            });
        });
    }
}

export function updateTask(taskObject) {
    return dispatch => {
        $.ajax({
            type: "PUT",
            url: "/api/task/" + taskObject.id,
            dataType: 'json',
            data: JSON.stringify(taskObject),
            contentType: 'application/json'
        }).done(function (data, textStatus, jqXHR) {
            dispatch({
                type: con.UPDATE_TASK_SUCCESS,
                newTask: data
            });
        });
    }
}

export function deleteTask(id) {
    return dispatch => {
        $.ajax({
            type: "DELETE",
            url: "/api/task/" + id
        }).done(function (data, textStatus, jqXHR) {
            dispatch({
                type: con.DELETE_TASK_SUCCESS,
                taskId: id
            });
        });
    }
}