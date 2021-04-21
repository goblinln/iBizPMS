import SubTask_en_US_Base from './sub-task_en_US_base';

function getLocaleResource(){
    const SubTask_en_US_OwnData = {};
    const targetData = Object.assign(SubTask_en_US_Base(), SubTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;