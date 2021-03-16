import IBzDoc_zh_CN_Base from './ibz-doc_zh_CN_base';

function getLocaleResource(){
    const IBzDoc_zh_CN_OwnData = {};
    const targetData = Object.assign(IBzDoc_zh_CN_Base(), IBzDoc_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;