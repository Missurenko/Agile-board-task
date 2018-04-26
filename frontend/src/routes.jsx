import React from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom'

import App from './containers/App/';
import LoginApp from './containers/Login/';

import Header from './containers/Header/index';
import Footer from './containers/Footer/index';
import Menu from './containers/Menu';

import {DashboardPage} from './containers/Dashboard';
import {VmsPage} from './containers/Vms';
import {WatchlistPage} from './containers/Watchlist';
import {PropertiesPage} from './containers/Properties';
import {AdministrationPage} from './containers/Administration';
import {LogsPage} from './containers/Logs';

import {PAGE_PATH} from './pathCons';

import './css/router.css'

export default () => (
    <div>
        <BrowserRouter>
            <div>
                <div className="container-fluid">
                    <div className="header">
                        <Header/>
                    </div>
                    <div className="row main-content">
                        <div className="col-md-2 menu-bar">
                            <Menu/>
                        </div>
                        <div className="col-md-10 content-container">
                            <Switch>
                                <Route exact path='/' component={App}/>
                                <Route path={PAGE_PATH.login} component={LoginApp}/>
                                <Route path={PAGE_PATH.dashboard} component={DashboardPage}/>
                                <Route path={PAGE_PATH.vms} component={VmsPage}/>
                                <Route path={PAGE_PATH.watchlist} component={WatchlistPage}/>
                                <Route path={PAGE_PATH.properties} component={PropertiesPage}/>
                                <Route path={PAGE_PATH.administration} component={AdministrationPage}/>
                                <Route path={PAGE_PATH.logs} component={LogsPage}/>
                            </Switch>
                        </div>
                    </div>
                    <div className="footer">
                        <Footer/>
                    </div>
                </div>
            </div>
        </BrowserRouter>
    </div>
);
