import Vue from 'vue';
import Vuex from 'vuex';

import { rootstate } from './state';
// import * as actions from './actions';
import * as mutations from './mutations';
import * as getters from './getters';

import authresource from './modules/auth-resource'
import loadingService from './modules/loading-service'

const state = {
    ...rootstate
};

Vue.use(Vuex);

const store = new Vuex.Store({
    state,
    // actions,
    mutations,
    getters,
    modules: {
        authresource,
        loadingService
    },
});

export default store;
