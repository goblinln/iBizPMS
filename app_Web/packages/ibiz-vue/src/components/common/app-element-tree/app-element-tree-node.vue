<template>
    <div
        class="app-element-tree-node el-tree-node"
        @click.stop="handleClick"
        @contextmenu="$event => this.handleContextMenu($event)"
        v-show="node.visible"
        :class="{
            'is-expanded': expanded,
            'is-current': node.isCurrent,
            'is-hidden': !node.visible,
            'is-focusable': !node.disabled,
            'is-checked': !node.disabled && node.checked,
            [`tree-node-id-${node.id}`]: true
        }"
        role="treeitem"
        tabindex="-1"
        :aria-expanded="expanded"
        :aria-disabled="node.disabled"
        :aria-checked="node.checked"
        :draggable="tree.draggable"
        @dragstart.stop="handleDragStart"
        @dragover.stop="handleDragOver"
        @dragend.stop="handleDragEnd"
        @drop.stop="handleDrop"
        ref="node"
    >
        <div class="el-tree-node__content" :style="{ 'padding-left': (node.level - 1) * tree.indent + 'px' }">
            <span
                @click.stop="handleExpandIconClick"
                :class="[
                    { 'is-leaf': node.isLeaf, expanded: !node.isLeaf && expanded },
                    'el-tree-node__expand-icon',
                    tree.iconClass ? tree.iconClass : 'el-icon-caret-right',
                ]"
            >
            </span>
            <el-checkbox
                v-if="showCheckbox && node.data.enablecheck"
                v-model="node.checked"
                :indeterminate="node.indeterminate"
                :disabled="!!node.disabled"
                @click.native.stop
                @change="handleCheckChange"
            >
            </el-checkbox>
            <span v-if="node.loading" class="el-tree-node__loading-icon el-icon-loading"> </span>
            <node-content
                v-if="!isEditable || !node.data.allowEditText"
                :node="node"
                @dblclick.native="handleDBlclick($event, node)"
            ></node-content>
            <el-input
                class="app-tree-node-input"
                v-focus
                v-if="isEditable && node.data.allowEditText"
                size="small"
                v-model="inputvalue"
                @blur="handleValueChange($event, node)"
            ></el-input>
        </div>
        <el-collapse-transition>
            <div
                class="el-tree-node__children"
                v-if="!renderAfterExpand || childNodeRendered"
                v-show="expanded"
                role="group"
                :aria-expanded="expanded"
            >
                <app-element-tree-node
                    :render-content="renderContent"
                    v-for="child in node.childNodes"
                    :render-after-expand="renderAfterExpand"
                    :show-checkbox="showCheckbox"
                    :key="getNodeKey(child)"
                    :node="child"
                    @node-expand="handleChildNodeExpand"
                >
                </app-element-tree-node>
            </div>
        </el-collapse-transition>
    </div>
</template>

<script>
import ElTreeNode from 'element-ui/packages/tree/src/tree-node.vue';
export default {
    name: 'AppElementTreeNode',

    componentName: 'AppElementTreeNode',

    mixins: [ElTreeNode],

    directives: {
        focus: {
            inserted: function (el) {
                el.querySelector('input').focus();
            },
        },
    },

    props: {},
    data() {
        return {
            isEditable: false,
        };
    },
    computed: {
        inputvalue: {
            set(value) {
                this.tree.$emit('edit-value-change', value, this.node, this);
            },
            get() {
                return this.node.label;
            },
        },
    },
    methods: {
        handleDBlclick(event, node) {
            event.stopPropagation();
            this.isEditable = true;
        },
        handleValueChange(event, node) {
            this.tree.$emit('close-edit', this.node, this);
            event.stopPropagation();
            this.isEditable = false;
        },
    },
};
</script>
<style lang="less">
.app-element-tree-node {
    .el-input--small .el-input__inner {
        height: 24px !important;
        line-height: 24px !important;
    }
    .el-tree-node.is-expanded > .el-tree-node__children {
        padding-top: 2px;
    }
}
</style>
