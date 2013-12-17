#include <cstdio>
#include <vector>
#include <map>
#include <algorithm>
#include <string>
#define DFS_WHITE -1
using namespace std;

typedef vector<int> vi;
vector<vi> AdjList;
int dfsNumberCounter;
vi dfs_num;
vi dfs_low;
vi dfs_parent;
typedef pair<int,int> ii;
vector<ii> critical;
int qtd;
void bridges(int u) {
    dfs_num[u] = dfs_low[u] = dfsNumberCounter++;
    for (int j = 0; j < (int)AdjList[u].size(); ++j) {
        int v = AdjList[u][j];
        if(dfs_num[v] == DFS_WHITE) {
            dfs_parent[v] = u;
            bridges(v);
            if(dfs_low[v] > dfs_num[u]) {
                u < v ? critical.push_back(ii(u,v)) : critical.push_back(ii(v,u));
                ++qtd;
            }
            dfs_low[u] = min(dfs_low[u],dfs_low[v]);
        } else if (v != dfs_parent[u]) {
            dfs_low[u] = min(dfs_low[u],dfs_num[v]);
        }
    }
}

int main () {
    int V,CL;
    while(scanf("%d",&V) != EOF) {
        AdjList.assign(V,vi()); dfs_num.assign(V,-1);
        dfs_low.assign(V,0); dfs_parent.assign(V,0);
        critical.clear(); qtd = 0;
        dfsNumberCounter = 0;
        for(int j = 0; j < V; ++j) {
            int a,b,n;
            scanf("%d (%d)",&a,&n);
            while(n--) {
                scanf("%d",&b);
                AdjList[a].push_back(b);
            }
        }
        for(int i = 0; i < V; ++i) {
            if(dfs_num[i] == DFS_WHITE) {
                bridges(i);
            }
        }
        sort(critical.begin(),critical.end());
        printf("%d critical links\n",qtd);
        for(int i = 0; i < qtd; ++i) {
            printf("%d - %d\n",critical[i].first,critical[i].second);
        }
        printf("\n");
    }
    return 0;
}