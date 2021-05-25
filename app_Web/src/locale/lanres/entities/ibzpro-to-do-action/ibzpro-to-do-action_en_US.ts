import IBZProToDoAction_en_US_Base from './ibzpro-to-do-action_en_US_base';

function getLocaleResource(){
    const IBZProToDoAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZProToDoAction_en_US_Base(), IBZProToDoAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;