import ProductBranch_zh_CN_Base from './product-branch_zh_CN_base';

function getLocaleResource(){
    const ProductBranch_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductBranch_zh_CN_Base(), ProductBranch_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;