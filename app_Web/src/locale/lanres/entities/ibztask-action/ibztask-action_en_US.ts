import IBZTaskAction_en_US_Base from './ibztask-action_en_US_base';

function getLocaleResource(){
    const IBZTaskAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZTaskAction_en_US_Base(), IBZTaskAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;