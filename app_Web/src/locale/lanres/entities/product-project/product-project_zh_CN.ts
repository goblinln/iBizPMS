import ProductProject_zh_CN_Base from './product-project_zh_CN_base';

function getLocaleResource(){
    const ProductProject_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductProject_zh_CN_Base(), ProductProject_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;