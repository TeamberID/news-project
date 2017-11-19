<aside id="sidebar" class="col-lg-4">
    <section id="tickets-popular" class="card border-warning">

        <div class="card-header border-warning bg-warning text-white">
            <h5 class="card-title">
                <strong>Популярные</strong>
            </h5>
        </div>
    <#list topNews as topNew>
    <div class="card-body">
        <div class="ticket-row" title="Количество просмотров: 164446">
            <div class="ticket-pagetitle">
                <h5>${topNew.pubDate}</h5>
                <a href="/news?id=${topNew.id}">${topNew.title}</a></div>
        </div>
    </#list>
    </div>
    </section>
</aside>
