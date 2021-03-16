import Task_en_US_Base from './task_en_US_base';

function getLocaleResource(){
    const Task_en_US_OwnData = {};
    const targetData = Object.assign(Task_en_US_Base(), Task_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;