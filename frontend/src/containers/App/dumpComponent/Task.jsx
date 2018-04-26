import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from "react-redux";
import * as actions from "../actions";
import {bindActionCreators} from "redux";

class Task extends Component {


    static propTypes = {
        taskObject: PropTypes.shape({
            id: PropTypes.string,
            name: PropTypes.string,
            description: PropTypes.string,
            boardId: PropTypes.string
        }).isRequired
    };

    constructor(props) {
        super(props);
        this.taskObject = this.props.taskObject;
        this.isEditable = false;
        this.boardsDataList = this.props.boardsData.boardList;
        this.bindFunctions();
    }

    shouldComponentUpdate(nextProps) {
        this.forceUpdate();
        return true;
    }

    bindFunctions() {
        this.deleteTask = this.deleteTask.bind(this);
        this.updateTask = this.updateTask.bind(this);
        this.startEdit = this.startEdit.bind(this);
        this.cancelEdit = this.cancelEdit.bind(this);
    }

    updateTask() {
        let taskExample = {
            "id": this.taskObject.id,
            "name": this.taskName.value,
            "description": this.taskDescription.value,
            "boardId": this.taskBoardId.value
        };
        let self = this;
        Promise.resolve(this.props.actions.updateTask(taskExample)) // dispatch
            .then(function (response) {
                self.props.actions.getTaskList(); //dispatch
            }).then(function (responce) {
            self.cancelEdit();
        });
    }

    cancelEdit() {
        this.isEditable = false;
        this.forceUpdate();//todo rewrite
    }

    startEdit() {
        this.isEditable = true;
        this.forceUpdate();//todo rewrite
    }

    deleteTask() {
        this.props.actions.deleteTask(this.taskObject.id);
    }

    render() {
        return (
            <div className="task">
                <div className="task-delete">
                    <button onClick={this.deleteTask}>X
                    </button>
                </div>
                <div onClick={this.startEdit}>
                    <b>Id:</b>
                    {this.taskObject.id}
                    <br/>
                    <b>Name:</b>
                    {(this.isEditable) ? <input ref={(input) => this.taskName = input}
                                                defaultValue={this.taskObject.name}/> : this.taskObject.name}

                    <br/>
                    <br/>
                    <b>Description:</b>
                    {(this.isEditable) ? <input ref={(input) => this.taskDescription = input}
                                                defaultValue={this.taskObject.description}/> : this.taskObject.description}
                    <br/>
                    <div hidden={(!this.isEditable)}>
                        <b>Board:</b>
                        <select defaultValue={this.taskObject.boardId} ref={(input) => this.taskBoardId = input}>
                            {this.boardsDataList.map((element, key) =>
                                <option value={element.id} key={key}>{element.id}-{element.name}</option>
                            )}
                        </select>
                    </div>
                </div>

                <div className="task-save" hidden={(!this.isEditable)}>
                    <button onClick={this.updateTask}>SAVE
                    </button>
                    <button onClick={this.cancelEdit}>Cansel
                    </button>
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
)(Task);