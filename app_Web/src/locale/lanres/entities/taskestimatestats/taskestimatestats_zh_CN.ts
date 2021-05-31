import taskestimatestats_zh_CN_Base from './taskestimatestats_zh_CN_base';

function getLocaleResource(){
    const taskestimatestats_zh_CN_OwnData = {};
    const targetData = Object.assign(taskestimatestats_zh_CN_Base(), taskestimatestats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;