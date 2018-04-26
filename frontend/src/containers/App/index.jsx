import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from "redux";

import Board from './dumpComponent/Board';

import * as actions from "./actions";

//Умный компонент
export class AppPage extends Component {

    constructor(props) {
        super(props);

        this.createBoard = this.createBoard.bind(this);
        this.updateBoards = this.updateBoards.bind(this);
        this.updateTasks = this.updateTasks.bind(this);
    }

    componentWillMount() {
        this.props.actions.getBoardList();
    }

    componentDidMount() {
    }

    createBoard() {
        let boardObject = {name: this.newBoardName.value};
        this.newBoardName.value = '';
        let self = this;
        Promise.resolve(this.props.actions.createBoard(boardObject)) // dispatch
            .then(function (response) {
                self.props.actions.getBoardList();
            })
            .then(function (responce) {
                self.props.actions.getTaskList();
            });
    }

    updateBoards() {
        let self = this;
        Promise.resolve(this.props.actions.getBoardList()) // dispatch
            .then(function (responce) {
                self.props.actions.getTaskList();
            });
    }

    updateTasks() {
        this.props.actions.getTaskList();
    }

    shouldComponentUpdate(nextProps) {
        let sizeChanged = this.props.boardsData.boardList.length !== nextProps.boardsData.boardList.length;
        return true;
    }

    render() {
        return (
            <div className="container-fluid">
                <div>
                    New board name:
                    <input ref={(input) => this.newBoardName = input}/>
                    <button onClick={this.createBoard}>Add Board</button>
                </div>
                Boards
                <button onClick={this.updateTasks}>GetTasks</button>
                <button onClick={this.updateBoards}>UpdateBoards</button>
                <div className="boardList">
                    {this.props.boardsData.boardList.map((item, index) =>
                        <Board boardObject={item} key={item.id}
                        />
                    )}
                </div>
            </div>
        )
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
)(AppPage);