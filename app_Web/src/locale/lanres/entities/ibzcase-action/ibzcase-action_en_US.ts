import IBZCaseAction_en_US_Base from './ibzcase-action_en_US_base';

function getLocaleResource(){
    const IBZCaseAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZCaseAction_en_US_Base(), IBZCaseAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;