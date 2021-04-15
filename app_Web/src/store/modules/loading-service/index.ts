import { loadingState } from './state';
import * as mutations from './mutations';
import * as getters from './getters';

const state = {
    ...loadingState
}

export default {
    namespaced: true,
    state,
    getters,
    mutations
}