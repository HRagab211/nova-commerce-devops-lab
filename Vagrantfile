# frozen_string_literal: true

# Nova Commerce DevOps Lab
# Challenge mode: this file defines the topology. Provisioning details are intentionally left for the student.

VAGRANT_BOX = ENV.fetch('VAGRANT_BOX', 'ubuntu/jammy64')
PROJECT_SYNC_PATH = ENV.fetch('PROJECT_SYNC_PATH', '/workspace/nova-commerce')

machines = [
  { name: 'db01',      ip: '192.168.56.21', role: 'postgresql' },
  { name: 'cache01',   ip: '192.168.56.22', role: 'redis' },
  { name: 'mq01',      ip: '192.168.56.23', role: 'rabbitmq' },
  { name: 'search01',  ip: '192.168.56.24', role: 'elasticsearch' },
  { name: 'app01',     ip: '192.168.56.25', role: 'spring-boot' },
  { name: 'web01',     ip: '192.168.56.26', role: 'nginx' },
  { name: 'monitor01', ip: '192.168.56.27', role: 'prometheus-grafana' }
]

Vagrant.configure('2') do |config|
  config.vm.box = VAGRANT_BOX
  config.vm.synced_folder '.', PROJECT_SYNC_PATH, type: 'virtualbox'

  config.vm.provider 'virtualbox' do |vb|
    vb.linked_clone = true if Vagrant::VERSION >= '1.8.0'
    vb.memory = 1024
    vb.cpus = 1
  end

  machines.each do |machine|
    config.vm.define machine[:name] do |node|
      node.vm.hostname = machine[:name]
      node.vm.network 'private_network', ip: machine[:ip]

      node.vm.provider 'virtualbox' do |vb|
        vb.name = "nova-#{machine[:name]}"
        vb.memory = machine[:name] == 'search01' || machine[:name] == 'monitor01' ? 2048 : 1024
      end

      node.vm.provision 'shell', path: 'vagrant/provision/bootstrap.sh', args: [machine[:name], machine[:role]]
    end
  end
end
