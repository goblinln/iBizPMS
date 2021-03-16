import TaskStats_en_US_Base from './task-stats_en_US_base';

function getLocaleResource(){
    const TaskStats_en_US_OwnData = {};
    const targetData = Object.assign(TaskStats_en_US_Base(), TaskStats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;