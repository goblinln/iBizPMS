import taskestimatestats_en_US_Base from './taskestimatestats_en_US_base';

function getLocaleResource(){
    const taskestimatestats_en_US_OwnData = {};
    const targetData = Object.assign(taskestimatestats_en_US_Base(), taskestimatestats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;