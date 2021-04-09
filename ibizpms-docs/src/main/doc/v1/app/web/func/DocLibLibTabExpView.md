# 文档

文档库分页导航视图（文档）(DocLibLibTabExpView)

{% plantuml %}

digraph DocLibLibTabExpView {

label="文档";    

#rankdir=LR;

start [shape=circle,size="1,1",label="",style=filled, fillcolor=black]
end [shape=doublecircle,label=""]

own [shape=none, margin=0,size="6,6", label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">文档<BR/><BR/></TD>
</TR>
</TABLE>
>];

part [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">P</TD> 
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">





<BR/></TD>
</TR>
</TABLE>
>];


RecentUpdateQuickAccess [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">最近更新<BR/><BR/></TD>
</TR>
</TABLE>
>];



MyDocQuickAccess [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">我的文档<BR/><BR/></TD>
</TR>
</TABLE>
>];



MyFavouriteQuickAccess [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">我的收藏<BR/><BR/></TD>
</TR>
</TABLE>
>];



Create [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">创建文档库<BR/><BR/></TD>
</TR>
</TABLE>
>];



CreateFile [shape=none, margin=0, label=<
<TABLE WIDTH="150" BORDER="1" CELLBORDER="1" >
<TR>
<TD WIDTH="115" BORDER="0" COLSPAN="3"></TD><TD WIDTH="35" BORDER="0">V</TD>
</TR>
<TR>
<TD BORDER="0" COLSPAN="4" CELLPADDING="10">创建文档<BR/><BR/></TD>
</TR>
</TABLE>
>];



start -> own ;
own -> part [style=dashed,arrowhead=diamond,label="<包含>"] ;


part -> RecentUpdateQuickAccess [style=dashed,label=<最近更新<BR/>(迁移/popup)>];

RecentUpdateQuickAccess -> end ;



part -> MyDocQuickAccess [style=dashed,label=<我的文档<BR/>(迁移/popup)>];

MyDocQuickAccess -> end ;



part -> MyFavouriteQuickAccess [style=dashed,label=<我的收藏<BR/>(迁移/popup)>];

MyFavouriteQuickAccess -> end ;



part -> Create [style=dashed,label=<创建文档库<BR/>(迁移/popup)>];

Create -> end ;



part -> CreateFile [style=dashed,label=<创建文档<BR/>(迁移/popup)>];

CreateFile -> end ;



}

{% endplantuml %}

