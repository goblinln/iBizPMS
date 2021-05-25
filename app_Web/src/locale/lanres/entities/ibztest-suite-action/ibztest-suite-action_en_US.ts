import IBZTestSuiteAction_en_US_Base from './ibztest-suite-action_en_US_base';

function getLocaleResource(){
    const IBZTestSuiteAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZTestSuiteAction_en_US_Base(), IBZTestSuiteAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;