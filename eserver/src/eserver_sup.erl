
-module(eserver_sup).

-behaviour(supervisor).

%% API
-export([start_link/0]).

%% Supervisor callbacks
-export([init/1]).

%% Helper macro for declaring children of supervisor
-define(CHILD(I, Type), {I, {I, start_link, []}, permanent, 5000, Type, [I]}).

%% ===================================================================
%% API functions
%% ===================================================================

start_link() ->
    supervisor:start_link({local, ?MODULE}, ?MODULE, []).

%% ===================================================================
%% Supervisor callbacks
%% ===================================================================

init([]) ->
    Processes = [gui_interface()],
    Strategy = {one_for_one, 5, 10},
    {ok, {Strategy, lists:flatten(Processes)}}.
    
gui_interface() ->
    Cfg = [],
    ModName = gui_interface_server,
    Mfa = {gui_interface_server, start_link, [Cfg]},
    {ModName, Mfa, permanent, 5000, worker, dynamic}.

