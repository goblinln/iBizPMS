import TaskEstimate_en_US_Base from './task-estimate_en_US_base';

function getLocaleResource(){
    const TaskEstimate_en_US_OwnData = {};
    const targetData = Object.assign(TaskEstimate_en_US_Base(), TaskEstimate_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;