import IBZDailyAction_en_US_Base from './ibzdaily-action_en_US_base';

function getLocaleResource(){
    const IBZDailyAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZDailyAction_en_US_Base(), IBZDailyAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;