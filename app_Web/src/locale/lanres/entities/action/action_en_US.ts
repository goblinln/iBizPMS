import Action_en_US_Base from './action_en_US_base';

function getLocaleResource(){
    const Action_en_US_OwnData = {};
    const targetData = Object.assign(Action_en_US_Base(), Action_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;