import SubTask_zh_CN_Base from './sub-task_zh_CN_base';

function getLocaleResource(){
    const SubTask_zh_CN_OwnData = {};
    const targetData = Object.assign(SubTask_zh_CN_Base(), SubTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;