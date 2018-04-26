import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from "react-redux";
import * as actions from "../actions";
import Task from './Task';

import {bindActionCreators} from "redux";


class Board extends Component {

    static propTypes = {
        boardObject: PropTypes.shape({
            id: PropTypes.string,
            name: PropTypes.string,
            task: PropTypes.array
        }).isRequired,
        onLoadFunction: PropTypes.func
    };

    constructor(props) {
        super(props);
        this.boardObject = this.props.boardObject;
        this.bindFunctions();
    }

    shouldComponentUpdate(nextProps) {
        this.forceUpdate();
        return true;
    }

    componentWillMount() {
        this.props.actions.getTaskList();
    }

    bindFunctions() {
        this.deleteBoard = this.deleteBoard.bind(this);
        this.addTaskToBoard = this.addTaskToBoard.bind(this);
    }

    deleteBoard() {
        let self = this;
        Promise.resolve(this.props.actions.deleteBoard(this.boardObject.id)) // dispatch
            .then(function (response) {
                self.props.actions.getBoardList(); //dispatch
            })
            .then(function (responce) {
                self.props.actions.getTaskList(); //dispatch
            });
    }

    addTaskToBoard() {
        let taskExample = {
            "name": this.newTaskName.value,
            "description": this.newTaskDescription.value,
            "boardId": this.boardObject.id
        };
        this.props.actions.addTaskToBoard(taskExample);
        this.newTaskName.value = this.newTaskDescription.value = '';
    }

    render() {
        return (
            <div className="board">
                <div>
                    <div className="board-delete">
                        <button onClick={this.deleteBoard}>X
                        </button>
                    </div>
                    <div>
                        Id:{this.boardObject.id}<br/>
                        Name:{this.boardObject.name}
                    </div>
                </div>
                <div className="taskList">
                    {
                        (this.boardObject.task) ? this.props.boardObject.task.map((item, index) =>
                            <Task taskObject={item} key={item.id}/>
                        ) : ''
                    }
                </div>
                <div>
                    <b>Add Task</b>
                    <br/>
                    Name:
                    <input ref={(input) => {
                        this.newTaskName = input
                    }}/>
                    <br/>
                    Description:
                    <input ref={(input) => {
                        this.newTaskDescription = input
                    }}/>
                    <br/>
                    <button onClick={this.addTaskToBoard}>Add</button>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        boardsData: state.BoardReducer
    }
}

function actionsStateToProps(dispatch) {
    return {
        actions: bindActionCreators(actions, dispatch)
    }
}

export default connect(
    mapStateToProps,
    actionsStateToProps
)(Board);
