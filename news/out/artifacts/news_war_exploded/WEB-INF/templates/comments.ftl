<#list coms as com>
<div class="row comment" style="margin-top: 45px">
    <div class="comment-date" style="font-size: 10px">${com.pubDate}</div>
    <div class="col-md-2">
        <div class="to-centre">${com.user.login}</div>
    </div>
    <p class="col-md-10" style="margin-left: 0">
    ${com.description}
    </p>
</div>
</#list>


