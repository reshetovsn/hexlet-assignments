@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Hello Hexlet!</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <table class="table table-striped">
            @for(var post : page.getPosts())
                <tr>
                    <td>
                        ${post.getId()}
                    </td>
                    <td>
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                    </td>
                </tr>
            @endfor
        </table>
    </main>
    <nav>
        <ul>
            <li>
                <a href="${NamedRoutes.postsPath(page.getNumberOfPage() - 1)}">Prev</a>
            </li>
            <li>
                <a href="${NamedRoutes.postsPath(page.getNumberOfPage() + 1)}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>
